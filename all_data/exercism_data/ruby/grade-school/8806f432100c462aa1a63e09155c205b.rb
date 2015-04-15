class School
  attr_reader :db

  def initialize
    @db = {}
  end

  def add(student, grd)
    db[grd] ||= []
    student_list(grd) << student
  end

  def grade(grd)
    student_list(grd)
  end

  def sort
    sorted_grades.each_with_object({}) do |grd, all_grades|
      all_grades.update(grd => sorted_student_list(grd))
    end
  end

  private

  def student_list(grd)
    db[grd].to_a
  end

  def sorted_student_list(grd)
    student_list(grd).sort
  end

  def sorted_grades
    db.each_key.sort
  end
end
