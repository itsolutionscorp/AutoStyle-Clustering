class School
  def db
    @db ||= {}
  end
  
  def add name, grade_id
    db.store grade_id, grade(grade_id).push(name)
  end
  
  def grade grade_id
    db.fetch(grade_id,[])
  end
  
  def sort
    Hash[db.sort.map{|grade_id,students| [grade_id, students.sort!] }]
  end
end
