class School

  def add(name, grade)
    students.push({name: name, grade: grade})
  end

  def to_hash
    school_hash = Hash.new {|hash, key| hash[key] = Array.new } 
    sorted_students.each do |record|
      school_hash[record[:grade]].push(record[:name])
    end
    Hash[school_hash.sort]
  end

  def grade(grade_level)
    sorted_students.inject([]) {|names, record|
      if record[:grade] == grade_level
        names << record[:name]
      end
      names
    }
  end

  private
  def students
    @students ||= []
  end

  def sorted_students
    students.sort_by{|record| record[:name] }
  end
end
