package Kurs;
import Lab_1.*;
import Lab_2.*;
import Lab_3.*;  // Добавляем импорт для Lab 3
import Lab_4.*;  // Добавляем импорт для Lab 4
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp extends JFrame {
    // Панели для каждой лабораторной
    private JPanel lab1Panel;
    private JPanel lab2Panel;
    private JPanel lab3Panel;  // Добавляем панель для Lab 3
    private JPanel lab4Panel;  // Добавляем панель для Lab 4
    private JTabbedPane tabbedPane;
    
    // Компоненты для Lab 1
    private Context context;
    private JTextArea outputArea1;
    private JComboBox<String> strategyComboBox;
    
    // Компоненты для Lab 2
    private JTextArea outputArea2;
    
    // Компоненты для Lab 3
    private JTextArea outputArea3;
    private JTextArea inputArea3;
    private JButton translateButton3;
    private JButton loadDictionaryButton3;
    private JButton browseButton3;
    private JButton clearButton3;
    private JTextField filePathField3;
    private Translator translator3;
    private JFileChooser fileChooser3;
    
 // Компоненты для Lab 4
    private JTextArea outputArea4;
    private JButton executeButton4;
    private JButton clearButton4;
    // Добавляем поля для ввода данных
    private JTextField averageInputField;
    private JTextField prefixInputField;
    private JTextField uniqueSquaresInputField;
    private JTextField lastElementInputField;
    private JTextField sumEvenInputField;
    private JTextField mapInputField;
    
    public MainApp() {
        context = new Context();
        translator3 = new Translator();  // Создаем экземпляр переводчика
        fileChooser3 = new JFileChooser();  // Создаем диалог выбора файлов
        fileChooser3.setCurrentDirectory(new File("."));  // Устанавливаем текущую директорию
        initUI();
    }
    
    private void initUI() {
        setTitle("Лабораторные работы 1-4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);  // Увеличиваем размер окна для 4 вкладок
        setLocationRelativeTo(null);
        
        // Создаем главную панель с BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Создаем панель с вкладками
        tabbedPane = new JTabbedPane();
        
        // Создаем панели для каждой лабораторной
        lab1Panel = createLab1Panel();
        lab2Panel = createLab2Panel();
        lab3Panel = createLab3Panel();  // Создаем панель для Lab 3
        lab4Panel = createLab4Panel();  // Создаем панель для Lab 4
        
        // Добавляем панели во вкладки
        tabbedPane.addTab("Lab 1: Стратегия", lab1Panel);
        tabbedPane.addTab("Lab 2: Аннотации", lab2Panel);
        tabbedPane.addTab("Lab 3: Переводчик", lab3Panel);  // Добавляем третью вкладку
        tabbedPane.addTab("Lab 4: Stream API", lab4Panel);  // Добавляем четвертую вкладку
        
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel);
    }
    
    // Панель для Lab 1 (без изменений)
    private JPanel createLab1Panel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Панель управления
        JPanel controlPanel = createLab1ControlPanel();
        
        // Текстовая область для вывода
        outputArea1 = new JTextArea(15, 50);
        outputArea1.setEditable(false);
        outputArea1.setLineWrap(true);
        outputArea1.setWrapStyleWord(true);
        outputArea1.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(outputArea1);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результат"));
        
        
        // Добавляем компоненты
        panel.add(controlPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createLab1ControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Управление героем"));
        
        // Метки и выпадающий список
        JLabel label = new JLabel("Выберите стратегию движения:");
        strategyComboBox = new JComboBox<>(new String[]{
            "Стоять", "Бежать", "Плавать", "Идти"
        });
        
        // Кнопки
        JButton executeButton = new JButton("Применить стратегию");
        JButton clearButton = new JButton("Очистить вывод");
        
        // Обработчики событий
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyLab1Strategy();
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea1.setText("");
            }
        });
        
        // Добавление компонентов
        panel.add(label);
        panel.add(strategyComboBox);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(executeButton);
        panel.add(clearButton);
        
        return panel;
    }
    
   
    // Панель для Lab 2 (без изменений)
    private JPanel createLab2Panel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Панель управления
        JPanel controlPanel = createLab2ControlPanel();
        
        // Текстовая область для вывода
        outputArea2 = new JTextArea(15, 50);
        outputArea2.setEditable(false);
        outputArea2.setLineWrap(true);
        outputArea2.setWrapStyleWord(true);
        outputArea2.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(outputArea2);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результат выполнения методов"));
        
        
        // Добавляем компоненты
        panel.add(controlPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createLab2ControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Управление Lab 2"));
        
        // Кнопки
        JButton executeButton = new JButton("Вызвать методы с аннотациями");
        JButton clearButton = new JButton("Очистить вывод");
        
        // Обработчики событий
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeLab2Methods();
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea2.setText("");
            }
        });
        
        // Добавление компонентов
        panel.add(executeButton);
        panel.add(clearButton);
        
        return panel;
    }
    
    // Панель для Lab 3 (с полем выбора пути)
    private JPanel createLab3Panel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Панель управления
        JPanel controlPanel = createLab3ControlPanel();
        
        // Панель ввода текста
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Введите текст для перевода"));
        
        inputArea3 = new JTextArea(5, 50);
        inputArea3.setLineWrap(true);
        inputArea3.setWrapStyleWord(true);
        inputArea3.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane inputScrollPane = new JScrollPane(inputArea3);
        inputPanel.add(inputScrollPane, BorderLayout.CENTER);
        
        // Текстовая область для вывода
        outputArea3 = new JTextArea(10, 50);
        outputArea3.setEditable(false);
        outputArea3.setLineWrap(true);
        outputArea3.setWrapStyleWord(true);
        outputArea3.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane outputScrollPane = new JScrollPane(outputArea3);
        outputScrollPane.setBorder(BorderFactory.createTitledBorder("Результат перевода и журнал операций"));
        
        // Добавляем компоненты
        panel.add(controlPanel, BorderLayout.NORTH);
        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(outputScrollPane, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createLab3ControlPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Управление переводчиком"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Метка и поле для пути к файлу
        JLabel fileLabel = new JLabel("Путь к файлу словаря:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        panel.add(fileLabel, gbc);
        
        filePathField3 = new JTextField(30);
        filePathField3.setText("src/Lab_3/dictionary.txt");  // Путь по умолчанию
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panel.add(filePathField3, gbc);
        
        // Кнопка обзора
        browseButton3 = new JButton("Обзор...");
        browseButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                browseForDictionaryFile();
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        panel.add(browseButton3, gbc);
        
        // Кнопки управления
        loadDictionaryButton3 = new JButton("Загрузить словарь");
        loadDictionaryButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadLab3Dictionary();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(loadDictionaryButton3, gbc);
        
        translateButton3 = new JButton("Перевести текст");
        translateButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                translateLab3Text();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(translateButton3, gbc);
        
        clearButton3 = new JButton("Очистить всё");
        clearButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputArea3.setText("");
                outputArea3.setText("");
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(clearButton3, gbc);
        
        return panel;
    }
    
    private JPanel createLab4Panel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Панель управления с полями ввода
        JPanel controlPanel = createLab4ControlPanel();
        
        // Текстовая область для вывода
        outputArea4 = new JTextArea(20, 60);
        outputArea4.setEditable(false);
        outputArea4.setLineWrap(true);
        outputArea4.setWrapStyleWord(true);
        outputArea4.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(outputArea4);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результаты выполнения методов Stream API"));
        
        // Добавляем компоненты
        panel.add(controlPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createLab4ControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Управление Lab 4: Stream API"));
        
        // Панель для кнопок
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        executeButton4 = new JButton("Выполнить все методы");
        clearButton4 = new JButton("Очистить вывод");
        
        executeButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeLab4Methods();
            }
        });
        
        clearButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea4.setText("");
            }
        });
        
        buttonPanel.add(executeButton4);
        buttonPanel.add(clearButton4);
        
        // Панель для полей ввода
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 5, 2, 5);
        
        // 1. averageOfList
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        inputPanel.add(new JLabel("1. Числа (через запятую):"), gbc);
        
        averageInputField = new JTextField("1,2,3,4,5", 20);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.7;
        inputPanel.add(averageInputField, gbc);
        
        // 2. addPrefixAndUpperCase
        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("2. Строки (через запятую):"), gbc);
        
        prefixInputField = new JTextField("apple,banana,cherry", 20);
        gbc.gridx = 1; gbc.gridy = 1;
        inputPanel.add(prefixInputField, gbc);
        
        // 3. squaresOfUniqueElements
        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("3. Числа для уникальных квадратов:"), gbc);
        
        uniqueSquaresInputField = new JTextField("1,2,2,3,4,4,5", 20);
        gbc.gridx = 1; gbc.gridy = 2;
        inputPanel.add(uniqueSquaresInputField, gbc);
        
        // 4. getLastElement
        gbc.gridx = 0; gbc.gridy = 3;
        inputPanel.add(new JLabel("4. Элементы коллекции:"), gbc);
        
        lastElementInputField = new JTextField("first,second,third", 20);
        gbc.gridx = 1; gbc.gridy = 3;
        inputPanel.add(lastElementInputField, gbc);
        
        // 5. sumOfEvenNumbers
        gbc.gridx = 0; gbc.gridy = 4;
        inputPanel.add(new JLabel("5. Числа для суммы четных:"), gbc);
        
        sumEvenInputField = new JTextField("1,2,3,4,5,6", 20);
        gbc.gridx = 1; gbc.gridy = 4;
        inputPanel.add(sumEvenInputField, gbc);
        
        // 6. mapFirstCharToRest
        gbc.gridx = 0; gbc.gridy = 5;
        inputPanel.add(new JLabel("6. Строки для Map:"), gbc);
        
        mapInputField = new JTextField("apple,banana,apricot,cherry", 20);
        gbc.gridx = 1; gbc.gridy = 5;
        inputPanel.add(mapInputField, gbc);
        
        // Добавляем панели
        panel.add(inputPanel);
        panel.add(buttonPanel);
        
        return panel;
    }
    
    // Метод для выбора файла через диалоговое окно
    private void browseForDictionaryFile() {
        // Настраиваем фильтр для текстовых файлов
        fileChooser3.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
            }
            
            @Override
            public String getDescription() {
                return "Текстовые файлы (*.txt)";
            }
        });
        
        int result = fileChooser3.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser3.getSelectedFile();
            filePathField3.setText(selectedFile.getAbsolutePath());
            
            // Автоматически загружаем словарь после выбора файла
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    loadLab3Dictionary();
                }
            });
        }
    }
    
    // Методы для Lab 1
    private void applyLab1Strategy() {
        int selectedIndex = strategyComboBox.getSelectedIndex();
        String result = "";
        
        switch (selectedIndex) {
            case 0:
                context.setStrategy(new ConcreteStrategyStand());
                result = context.moveStrategy();
                break;
            case 1:
                context.setStrategy(new ConcreteStrategyRun());
                result = context.moveStrategy();
                break;
            case 2:
                context.setStrategy(new ConcreteStrategySwim());
                result = context.moveStrategy();
                break;
            case 3:
                context.setStrategy(new ConcreteStrategyWalk());
                result = context.moveStrategy();
                break;
            default:
                result = "Неизвестная стратегия";
        }
        
        // Добавляем результат в текстовую область
        outputArea1.append("Выбрана стратегия: " + strategyComboBox.getSelectedItem() + "\n");
        outputArea1.append("Результат: " + result + "\n");
        outputArea1.append("-".repeat(50) + "\n");
        
        // Прокручиваем вниз
        outputArea1.setCaretPosition(outputArea1.getDocument().getLength());
    }
    
    // Методы для Lab 2
    private void executeLab2Methods() {
        outputArea2.setText(""); // Очищаем предыдущий вывод
        
        try {
            MyAnnotatedClass myObject = new MyAnnotatedClass();
            MethodCaller caller = new MethodCaller();
            
            // Перенаправляем вывод System.out в строку
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream old = System.out;
            System.setOut(ps);
            
            // Вызываем методы
            caller.callAnnotatedMethods(myObject);
            
            // Восстанавливаем оригинальный вывод
            System.out.flush();
            System.setOut(old);
            
            // Получаем результат
            String result = baos.toString();
            
            // Выводим результат
            outputArea2.append("=== Начало выполнения методов с аннотациями ===\n\n");
            outputArea2.append(result);
            outputArea2.append("\n=== Выполнение завершено ===\n");
            
        } catch (Exception e) {
            outputArea2.append("Ошибка при выполнении методов: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        
        // Прокручиваем вниз
        outputArea2.setCaretPosition(outputArea2.getDocument().getLength());
    }
    
    // Методы для Lab 3
    private void loadLab3Dictionary() {
        String filePath = filePathField3.getText().trim();
        
        if (filePath.isEmpty()) {
            outputArea3.append("Ошибка: Укажите путь к файлу словаря\n");
            outputArea3.append("-".repeat(50) + "\n");
            return;
        }
        
        try {
            // Проверяем существование файла
            File file = new File(filePath);
            if (!file.exists()) {
                outputArea3.append("Ошибка: Файл не существует: " + filePath + "\n");
                outputArea3.append("-".repeat(50) + "\n");
                return;
            }
            
            if (!file.canRead()) {
                outputArea3.append("Ошибка: Нет доступа для чтения файла: " + filePath + "\n");
                outputArea3.append("-".repeat(50) + "\n");
                return;
            }
            
            // Загружаем словарь
            translator3.loadDictionary(filePath);
            
            // Получаем количество записей через рефлексию (так как поле dictionary приватное)
            java.lang.reflect.Field dictField = translator3.getClass().getDeclaredField("dictionary");
            dictField.setAccessible(true);
            java.util.Map<?, ?> dict = (java.util.Map<?, ?>) dictField.get(translator3);
            
            outputArea3.append("Словарь успешно загружен из файла: " + filePath + "\n");
            outputArea3.append("Загружено записей: " + dict.size() + "\n");
            
            outputArea3.append("-".repeat(50) + "\n");
            
            // Прокручиваем вниз
            outputArea3.setCaretPosition(outputArea3.getDocument().getLength());
           
        } catch (Exception e) {
            outputArea3.append("Ошибка при загрузке словаря: " + e.getMessage() + "\n");
            outputArea3.append("-".repeat(50) + "\n");
            e.printStackTrace();
        }
    }
    
    private void translateLab3Text() {
        String textToTranslate = inputArea3.getText().trim();
        
        if (textToTranslate.isEmpty()) {
            outputArea3.append("Ошибка: Введите текст для перевода\n");
            outputArea3.append("-".repeat(50) + "\n");
            return;
        }
        
        try {
            // Выполняем перевод
            String translation = translator3.translate(textToTranslate);
            
            // Выводим результат
            outputArea3.append("Исходный текст: " + textToTranslate + "\n");
            outputArea3.append("Перевод: " + translation + "\n");
            outputArea3.append("-".repeat(50) + "\n");
            
            // Прокручиваем вниз
            outputArea3.setCaretPosition(outputArea3.getDocument().getLength());
            
        } catch (Exception e) {
            outputArea3.append("Ошибка при переводе: " + e.getMessage() + "\n");
            outputArea3.append("-".repeat(50) + "\n");
        }
    }
    
    // Методы для Lab 4
    private void executeLab4Methods() {
        outputArea4.setText(""); // Очищаем предыдущий вывод
        
        try {
            // Перенаправляем вывод System.out в строку
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream old = System.out;
            System.setOut(ps);
            
            // Вызываем тестовые методы из класса Main
            testLab4Methods();
            
            // Восстанавливаем оригинальный вывод
            System.out.flush();
            System.setOut(old);
            
            // Получаем результат
            String result = baos.toString();
            
        } catch (Exception e) {
            outputArea4.append("Ошибка при выполнении методов: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        
        // Прокручиваем вниз
        outputArea4.setCaretPosition(outputArea4.getDocument().getLength());
    }
    
    private void testLab4Methods() {
        outputArea4.append("=== Введенные данные ===\n");
        
        try {
            // 1. averageOfList
            outputArea4.append("1. Метод averageOfList:\n");
            List<Integer> numbers1 = parseIntegerList(averageInputField.getText());
            outputArea4.append("   Входные данные: " + numbers1 + "\n");
            OptionalDouble avg = Lab_4.Main.averageOfList(numbers1);
            avg.ifPresent(value -> outputArea4.append("   Среднее значение: " + value + "\n\n"));
            
            // 2. addPrefixAndUpperCase
            outputArea4.append("2. Метод addPrefixAndUpperCase:\n");
            List<String> strings1 = parseStringList(prefixInputField.getText());
            outputArea4.append("   Входные данные: " + strings1 + "\n");
            List<String> prefixedStrings = Lab_4.Main.addPrefixAndUpperCase(strings1);
            outputArea4.append("   Результат: " + prefixedStrings + "\n\n");
            
            // 3. squaresOfUniqueElements
            outputArea4.append("3. Метод squaresOfUniqueElements:\n");
            List<Integer> numbers2 = parseIntegerList(uniqueSquaresInputField.getText());
            outputArea4.append("   Входные данные: " + numbers2 + "\n");
            List<Integer> uniqueSquares = Lab_4.Main.squaresOfUniqueElements(numbers2);
            outputArea4.append("   Результат: " + uniqueSquares + "\n\n");
            
            // 4. getLastElement
            outputArea4.append("4. Метод getLastElement:\n");
            List<String> strings2 = parseStringList(lastElementInputField.getText());
            outputArea4.append("   Входные данные: " + strings2 + "\n");
            try {
                String lastElement = Lab_4.Main.getLastElement(strings2);
                outputArea4.append("   Последний элемент: " + lastElement + "\n\n");
            } catch (NoSuchElementException e) {
                outputArea4.append("   Ошибка: " + e.getMessage() + "\n\n");
            }
            
            // 5. sumOfEvenNumbers
            outputArea4.append("5. Метод sumOfEvenNumbers:\n");
            int[] numbers3 = parseIntArray(sumEvenInputField.getText());
            outputArea4.append("   Входные данные: " + Arrays.toString(numbers3) + "\n");
            int sumEven = Lab_4.Main.sumOfEvenNumbers(numbers3);
            outputArea4.append("   Сумма четных чисел: " + sumEven + "\n\n");
            
            // 6. mapFirstCharToRest
            outputArea4.append("6. Метод mapFirstCharToRest:\n");
            List<String> strings3 = parseStringList(mapInputField.getText());
            outputArea4.append("   Входные данные: " + strings3 + "\n");
            Map<Character, String> charMap = Lab_4.Main.mapFirstCharToRest(strings3);
            outputArea4.append("   Результат Map:\n");
            for (Map.Entry<Character, String> entry : charMap.entrySet()) {
                outputArea4.append("   '" + entry.getKey() + "' -> \"" + entry.getValue() + "\"\n");
            }
            outputArea4.append("\n");
            
        } catch (Exception e) {
            outputArea4.append("Ошибка при обработке данных: " + e.getMessage() + "\n");
        }
        
        outputArea4.append("=".repeat(50) + "\n\n");
    }

    // Вспомогательные методы для парсинга данных
    private List<Integer> parseIntegerList(String input) {
        if (input == null || input.trim().isEmpty()) {
            return Arrays.asList();
        }
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private List<String> parseStringList(String input) {
        if (input == null || input.trim().isEmpty()) {
            return Arrays.asList();
        }
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    private int[] parseIntArray(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new int[0];
        }
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }
    
    public static void main(String[] args) {
        // Запуск в потоке обработки событий Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainApp app = new MainApp();
                app.setVisible(true);
                
                // Начальное сообщение в Lab 1
                app.outputArea1.append("Лабораторная 1: Паттерн Стратегия\n");
                app.outputArea1.append("=".repeat(50) + "\n\n");
                
                // Начальное сообщение в Lab 2
                app.outputArea2.append("Лабораторная 2: Аннотации\n");
                app.outputArea2.append("=".repeat(50) + "\n\n");
                
                // Начальное сообщение в Lab 3
                app.outputArea3.append("Лабораторная 3: Переводчик\n");
                app.outputArea3.append("=".repeat(50) + "\n\n");
                
             // Начальное сообщение в Lab 4
                app.outputArea4.append("Лабораторная 4: Stream API\n");
                app.outputArea4.append("=".repeat(50) + "\n\n");
            }
        });
    }
}