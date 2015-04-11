class School
  attr_reader :db

  def initialize
    @db = Hash.new { |hash, key| hash[key] = [] }
  end

  def add(student_name, grade)
    @db[grade] << student_name
  end

  def grade(grade_num)
    @db[grade_num]
  end

  def sort
    @db.keys.sort.inject({}) {|sorted_result, grade| sorted_result.merge({grade => @db[grade].sort}) }
  end
end
