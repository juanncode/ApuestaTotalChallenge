**Desafío:** [Desafío Android - Vista de Historial de Apuestas](https://drive.google.com/drive/folders/1gp5UFm4llSyZT5wQge4i9l0RF4IUxbv-)

**Correo de envío:** maycol.elcorrobarrutia@apuestatotal.com  
**Asunto:** atptandroid-vista-de-historial-de-apuestas

**Cuerpo del correo:**  
Por favor, incluye la siguiente información en tu correo:

1. **Nombre y Apellidos**
2. **Archivo APK** para instalar en un dispositivo Android
3. **Link del repositorio público** en Github o Gitlab

---

### Desafío Técnico: Visualización de Apuestas

**Objetivo**: Desarrolla una app en android con Java/Kotlin para visualizar una lista de apuestas con la información proporcionada. Aplica diferentes estilos según el nivel de cada apuesta.

### **Requisitos**:

1. **Visualización**:

   - Muestra las apuestas con el diseño que prefieras (**cards**, **listas**, etc.).
   - Estiliza las apuestas según su **nivel**, siguiendo esta jerarquía:
     - **Ganadas**: Leyenda, King, Master, Capo, Cazafijas.
     - **Perdidas**: Donatelo.

2. **Componentes Clave**:

   - **Logo**.
   - **Input de búsqueda** para buscar por **EventName** (se encuentra en `BetSelections`).
   - **Filtro** que permita filtrar por algún criterio relevante (por ejemplo, **status** o **tipo de apuesta**).

3. **Diseño y Estilo**:

   - Usa **XML** o cualquier **sistema de diseño/libreria**.
   - Los estilos y colores dependen de ti, pero asegúrate de que cada nivel sea fácilmente identificable.

4. **Transiciones y Animaciones**:

   - Se valoran las animaciones y transiciones suaves en la interfaz.

5. **Cruce de Datos**:

   - Cruza la información de **bets** (campo `game`) con **betDetail** (campo `BetId`).

### **Datos Importantes**:

- Estos datos representan el **historial de apuestas de un usuario real**.
- Se evaluará la claridad en el diseño, la funcionalidad de búsqueda y filtrado, así como las animaciones.
- Cualquier flujo o vista adicional que desees incluir, como una página inicial de login para demostrar habilidades de seguridad, será valorado por el equipo durante la evaluación final.
