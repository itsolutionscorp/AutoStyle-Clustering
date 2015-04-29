class School
  attr_accessor :db

  def initialize
    @db = {}
  end

  def add(student, session)
    if db[session]
      db[session] << "#{student}"
    else
      db[session] = ["#{student}"]
    end
  end

  def grade(number)
    if db[number]
      db[number]
    else
      []
    end
  end

  def sort
    db.rehash
  end

end
