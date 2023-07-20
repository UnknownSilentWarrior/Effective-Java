# Chapter 10: Exception #
## Item 70: Use checked exception for recoverable condition and runtime exceptions for programming error
Throwables:
* checked exceptions: for conditions from which the caller can reasonably be expected to recover 
* unchecked exceptions: shouldn't, be caught. recovery is impossible and continued execution would do more harm than good.
  * runtime exceptions: to indicate programming errors. The great majority indicate precondition violations.
  * errors : are reserved for use by the JVM. (as a convention)

Unchecked throwables that you implement should always subclass RuntimeException.

## Item 72: Favor to use standard exception 
| Exception                       |  Occasion for Use                                                              |
|---------------------------------|--------------------------------------------------------------------------------|
| IllegalArgumentException        |  Non-null parameter value is inappropriate                                     |
| IllegalStateException           |  Object state is inappropriate for method invocation                           |
| NullPointerException            |  Parameter value is null where prohibited                                      |
| IndexOutOfBoundsException       |  Index parameter value is out of range                                         |
| ConcurrentModificationException |  Concurrent modification of an object has been detected where it is prohibited |
| UnsupportedOperationException   |  Object does not support method                                                |

### Java 8 Exceptions
| 					             |  					             | 					               |
|--------------------------------|-----------------------------------|---------------------------------|
| AclNotFoundException           | InvalidMidiDataException          | RefreshFailedException          |
| ActivationException            | InvalidPreferencesFormatException | RemarshalException              |
| AlreadyBoundException          | InvalidTargetObjectTypeException  | RuntimeException                |
| ApplicationException           | IOException                       | SAXException                    |
| AWTException                   | JAXBException                     | ScriptException                 |
| BackingStoreException          | JMException                       | ServerNotActiveException        |
| BadAttributeValueExpException  | KeySelectorException              | SOAPException                   |
| BadBinaryOpValueExpException   | LambdaConversionException         | SQLException                    |
| BadLocationException           | LastOwnerException                | TimeoutException                |
| BadStringOperationException    | LineUnavailableException          | TooManyListenersException       |
| BrokenBarrierException         | MarshalException                  | TransformerException            |
| CertificateException           | MidiUnavailableException          | TransformException              |
| CloneNotSupportedException     | MimeTypeParseException            | UnmodifiableClassException      |
| DataFormatException            | MimeTypeParseException            | UnsupportedAudioFileException   |
| DatatypeConfigurationException | NamingException                   | UnsupportedCallbackException    |
| DestroyFailedException         | NoninvertibleTransformException   | UnsupportedFlavorException      |
| ExecutionException             | NotBoundException                 | UnsupportedLookAndFeelException |
| ExpandVetoException            | NotOwnerException                 | URIReferenceException           |
| FontFormatException            | ParseException                    | URISyntaxException              |
| GeneralSecurityException       | ParserConfigurationException      | UserException                   |
| GSSException                   | PrinterException                  | XAException                     |
| IllegalClassFormatException    | PrintException                    | XMLParseException               |
| InterruptedException           | PrivilegedActionException         | XMLSignatureException           |
| IntrospectionException         | PropertyVetoException             | XMLStreamException              |
| InvalidApplicationException    | ReflectiveOperationException      | XPathException                  |


