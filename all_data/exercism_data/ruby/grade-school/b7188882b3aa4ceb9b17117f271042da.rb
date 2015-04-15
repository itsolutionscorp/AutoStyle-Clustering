class School
  def initialize name
    @name = name
  end

  def add student, student_grade
    grades.assign student, student_grade
  end

  def grade student_grade
    grades.students student_grade
  end

  def sort
   db.each_with_object({}) do |(grade, students), sorted_grades|
      sorted_grades[grade] = students.sort
    end
  end

  def db
    grades.all
  end

  private

  def grades
    @grades ||= Grades.new
  end

  class Grades
    def assign student, grade
      storage[grade] = students(grade).push student
    end

    def students grade
      storage.fetch(grade, []).clone
    end

    def all
      Hash[storage.sort]
    end

    private

    def storage
      @storage ||= {}
    end
  end
end
