class School

  def db
    @db ||= {} 
  end

  def add(name, number)
    db[number] ||= []
    db[number] << name
  end

  def grade(number)
    db[number] || []
  end

  def sort    
    sorted = {}
    db.sort.each do |number,names| 
      sorted[number] = db[number].sort
    end
    sorted
  end

end
