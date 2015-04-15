class School
  def initialize
    @graded_students = Hash.new{ |h, k| h[k] = [] }
  end

  def add name, grade
    (@graded_students[grade] << name).sort!
  end

  def grade g
    @graded_students[g]
  end

  def to_hash
    Hash[@graded_students.sort_by{|k, v| k }]
  end
end
