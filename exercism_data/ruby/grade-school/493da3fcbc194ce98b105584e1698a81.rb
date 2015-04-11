class School

  attr_reader :db

  def initialize
    @db = Hash.new([])
  end

  def add(name, grade)
    db[grade] += [name]
  end

  def grade(number)
    @db[number]
  end

  def sort
    sorted_grades.each_with_object({}) do |grade, sorted_db|
      sorted_db[grade] = sorted_names_for(grade)
    end
  end

  private

  def sorted_grades
    db.keys.sort
  end

  def sorted_names_for(grade)
    @db[grade].sort
  end
end
