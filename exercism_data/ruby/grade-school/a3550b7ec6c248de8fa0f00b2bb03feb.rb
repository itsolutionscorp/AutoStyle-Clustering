class School

  def initialize
    @students ||= Hash.new {|this, grade| this[grade] = [] }
  end

  def to_hash
    Hash[ students.sort ]
  end

  def add name, grade
    students[ grade ].push(name).sort!

    self
  end

  def grade number
    students[ number ].dup
  end

private

  attr_reader :students

end
