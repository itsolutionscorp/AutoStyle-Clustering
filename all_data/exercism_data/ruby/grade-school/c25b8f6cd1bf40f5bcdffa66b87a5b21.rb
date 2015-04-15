class School

  attr_accessor :db

  def initialize
    @db ||= Hash.new {|hash, key| hash[key] = []}
  end

  def add(name, grade)
    db[grade] << name
  end

  def grade(grade_level)
    db[grade_level]
  end

  def sort
    sorted_by_grade
  end

  private

  def sorted_by_grade
    db.keys.sort.each_with_object({}) do |grade, hash|
      hash[grade] = db[grade].sort
    end
  end

end
