class School
  def initialize
    @grades = Hash.new {|this, grade| this[grade] = [] }
  end

  def add(name, grade)
    @grades[grade].push(name).sort!
  end

  def grade(grade)
    @grades.fetch(grade, [])
  end

  def to_hash
    Hash[@grades.sort]
  end
end
