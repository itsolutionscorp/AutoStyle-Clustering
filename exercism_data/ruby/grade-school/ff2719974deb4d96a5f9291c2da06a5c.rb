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
    jumble = {}
    sorted_grades.map do
      |grd| jumble.update(grd => student_list(grd).sort)
    end
    jumble
  end

  private

  def student_list(grd)
    db[grd].to_a
  end

  def sorted_grades
    db.each_key.sort
  end
end
