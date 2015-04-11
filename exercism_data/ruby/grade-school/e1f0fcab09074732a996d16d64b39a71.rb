class School

  def initialize
    @database = {}
  end

  def db
    db = database.clone
    db.each do |k,v|
      k.freeze
      v.freeze
    end
    return db.freeze
  end

  def add(student, grade)
    database.fetch(grade){|g| database[g] = [] } << student
  end

  def grade(grade)
    database.fetch(grade, [])
  end

  def sort
    db.keys.sort.each_with_object({}){ |k,h| h[k] = db[k].sort }
  end

  private

  def database
    @database
  end
end
