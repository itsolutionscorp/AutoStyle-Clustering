class School
  attr_reader :db

  def initialize
    @db = {}
  end

  def add(student, grade_number)
    @db[grade_number] ||= []
    @db[grade_number] << student
  end

  def grade(grade_number)
    db.fetch(grade_number) { [] }
  end

  def sort
    grades_in_order.each_with_object({}) do |grade, sorted_db|
      sorted_db[grade] = db[grade].sort
    end
  end

  private

  def grades_in_order
    db.keys.sort
  end

end
