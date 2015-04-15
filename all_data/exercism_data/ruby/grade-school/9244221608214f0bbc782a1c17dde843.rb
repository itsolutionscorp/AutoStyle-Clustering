class School

  def initialize
    @roster = Hash.new{|hash, key| []}
  end

  def db
    @roster
  end

  def add student, grade
    @roster[grade] = @roster[grade] << student
  end

  def grade number
    @roster[number]
  end

  def sort
    Hash[Hash[ @roster.map{|grade,students| [grade, students.sort] } ].sort]
  end
end
