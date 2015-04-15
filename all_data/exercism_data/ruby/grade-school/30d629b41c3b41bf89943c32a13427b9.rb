class School
  
  def initialize
    @school = Hash.new {|hash, key| hash[key] = []}
  end

  def add(name, grade)
    @school[grade] << name
    nil
  end

  def grade(grade)
    @school[grade].clone
  end

  def sort
    copy_grades(@school.keys.sort, &:sort)
  end

  def db
    copy_grades(@school.keys)
  end

  private

  def copy_grades(grades, &block)
    grades.each_with_object({}) do |grade, result|
      result[grade] = block_given? ? yield(grade(grade)) : grade(grade)
    end
  end
end
