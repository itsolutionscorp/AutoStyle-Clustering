class School
  
  def initialize
    @school = Hash.new {|hash, key| hash[key] = []}
  end

  def add(name, grade)
    @school[grade] << name
  end

  def grade(grade)
    @school[grade]
  end

  def sort
    copy_grades(@school.keys.sort, &:sort)
  end

  def db
    copy_grades(@school.keys, &:clone)
  end

  private

  def copy_grades(grades, &block)
    grades.each_with_object({}) do |grade, result|
      result[grade] = yield(grade(grade))
    end
  end
end
