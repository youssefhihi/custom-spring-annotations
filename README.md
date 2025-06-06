# 💡 custom-spring-annotations

A collection of **custom Spring annotations** built to solve common challenges developers face in their day-to-day backend development. These annotations are designed to plug into any Spring Boot project and simplify repetitive or tricky concerns.

---

## ✅ Available Annotations

### 1. `@SmartLog`

**Purpose:**  
Logs detailed method execution information including:
- Method name  
- Execution time  
- Optional custom message  

**Usage:**
```java
@SmartLog("Processing order request")
public void processOrder() {
    // your logic
}
```

**How it helps:**
- Clean and consistent method logging  
- Helps in debugging and performance tracking without cluttering business logic  

---

### 2. `@PreventDuplicate`

**Purpose:**  
Prevents the **same method with the same input** from being executed multiple times within a specified time window.

**Use case:** Avoid issues like double form submissions or repeated API calls.

**Usage:**
```java
@PreventDuplicate(expiration = 10)
@PostMapping("/submit")
public ResponseEntity<?> submitForm(@RequestBody FormDto dto) {
    // your logic
}
```

**How it helps:**
- Avoids duplicate processing (like double orders or payments)  
- Lightweight alternative to distributed locking  

---

## 🛠️ Requirements

- Java 21+  
- Spring Boot 3.4+  
- Spring AOP dependency included via `spring-boot-starter`  

---


