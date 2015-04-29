class School

  def initialize
    @students = Hash.new []
  end

  def add name, grade
    students[ grade ] += [ name ]
    students[ grade ].sort!
  end

  def grade grade
    students[ grade ]
  end

  def to_hash
    Hash[ students.sort ]
  end

private

  attr_reader :students

end
