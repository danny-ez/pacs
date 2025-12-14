# PACS (Pancreatitis Acute Care Scores)

App Android offline para cálculo rápido de SIRS, BISAP, Marshall y estructuras para SOFA/APACHE II.

## Requisitos
- Android Studio Hedgehog o superior
- JDK 17
- Mínimo Android 9 (API 28)

## Cómo ejecutar en emulador
1. Abrir el proyecto en Android Studio.
2. Asegurar que el SDK Android 34 esté instalado.
3. Crear/emular un dispositivo con Android 9+.
4. Desde la barra de herramientas, elegir **Run > Run 'app'**.

## Generar APK debug
1. En Android Studio abrir **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
2. El APK se generará en `app/build/outputs/apk/debug/`.

## Checklist de mejoras futuras
- Exportar evaluaciones a PDF.
- Backup cifrado local/externo.
- Sincronización opcional via archivo seguro cuando se requiera.
- Internacionalización completa.
- Tablas SOFA y APACHE II completas con validaciones adicionales.
