class School
  def initialize
    @students = {}
  end

  def add(student, grade)
    @students[student] = grade
  end

  def to_hash
    Hash.new.tap do |hash|
      @students.values.uniq.sort.each { |num| hash[num] = grade(num) }
    end
  end

  def grade(num)
    @students.select { |key, value| value == num }.keys.sort
  end
end
