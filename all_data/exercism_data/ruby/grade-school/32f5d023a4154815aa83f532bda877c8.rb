class School

  def db
    @students
  end

  def add(name, grade)
    if @students[grade].nil?
      add_grade(grade)
      add_names_to_grade(name, grade)
    else
      add_names_to_grade(name, grade)
    end
  end

  def grade(grade)
    if @students.include?(grade)
      @students[grade]
    else
      add_grade(grade)
    end
  end

  def sort
    sort_elements_inside_values
    sort_by_keys
  end

  private

    def initialize
      @students = Hash.new
    end

    def sort_elements_inside_values
      @students.values.each {|array| array.sort!}
    end

    def sort_by_keys
      Hash[@students.sort]
    end

    def add_grade(grade)
      @students[grade] = []
    end

    def add_names_to_grade(name, grade)
      @students[grade].push(name)
    end
end
