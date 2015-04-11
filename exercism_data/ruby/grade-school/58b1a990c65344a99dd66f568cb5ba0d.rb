class School

  def to_hash
    Hash[ students_hash.sort ]
  end

  def add name, grade
    students_hash[ grade ] += [ name ]
    sort_students_in grade
  end

  def grade number
    students_hash[ number ]
  end

private
  
  def students_hash
    @students_hash ||= Hash.new []
  end

  def sort_students_in grade
    students_hash[ grade ].sort!
  end

end
