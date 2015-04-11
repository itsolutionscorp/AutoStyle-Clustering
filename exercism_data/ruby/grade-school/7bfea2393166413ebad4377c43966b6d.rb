class School

  def db
    @db ||= Hash.new do |hash, key|
      hash[key] = []
    end
  end
  
  def add(name, grade)
    db[grade] << name
  end

  def grade(grade)
    db[grade]
  end

  def sort
    Hash[
      db.sort_by {|grade, students| grade}
        .map {|grade, students| [grade, students.sort]}
    ]
  end

end
