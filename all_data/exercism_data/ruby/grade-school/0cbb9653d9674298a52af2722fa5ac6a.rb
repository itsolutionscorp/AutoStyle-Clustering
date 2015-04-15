class School
  def initialize name
    @name = name
  end

  def add student, student_grade
    database.assign student, student_grade
  end

  def grade student_grade
    database.students student_grade
  end

  def sort
    db.sort.each_with_object({}) do |(student_grade, students), sorted_db|
      sorted_db[student_grade] = students.sort
    end
  end

  def db
    database.to_h
  end

  private

  def database
    @database ||= GradingDatabase.new
  end

  class GradingDatabase
    def assign student, grade
      storage[grade] = students(grade).push student
    end

    def students grade
      storage.fetch(grade, []).clone
    end

    def to_h
      storage.clone
    end

    private

    def storage
      @storage ||= {}
    end
  end
end
