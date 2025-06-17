/**
 * Import function triggers from their respective submodules:
 *
 * const {onCall} = require("firebase-functions/v2/https");
 * const {onDocumentWritten} = require("firebase-functions/v2/firestore");
 *
 * See a full list of supported triggers at https://firebase.google.com/docs/functions
 */

const {onRequest} = require("firebase-functions/v2/https");
const logger = require("firebase-functions/logger");

const functions = require("firebase-functions");
const admin = require("firebase-admin");
admin.initializeApp();  // usará las credenciales del proyecto

exports.notifyNewTask = functions
  .region("us-central1")                  // ajusta región si quieres
  .firestore
  .document("tasks/{taskId}")             // colección “tasks”
  .onCreate(async (snap, context) => {
    const task = snap.data();
    const payload = {
      notification: {
        title: "📌 Nueva Tarea",
        body: task.title || "Tienes una nueva tarea",
      },
      data: {
        taskId: context.params.taskId,     // datos a tu gusto
      }
    };

    // Envía al topic "tasks"
    try {
      const response = await admin
        .messaging()
        .sendToTopic("tasks", payload);
      console.log("Notificación enviada:", response);
    } catch (err) {
      console.error("Error enviando notificación:", err);
    }
    return null;
  });


// Create and deploy your first functions
// https://firebase.google.com/docs/functions/get-started

// exports.helloWorld = onRequest((request, response) => {
//   logger.info("Hello logs!", {structuredData: true});
//   response.send("Hello from Firebase!");
// });
