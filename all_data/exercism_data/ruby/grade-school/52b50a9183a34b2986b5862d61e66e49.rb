class School
  attr_reader :db

  def initialize
    @db = Hash.new([])
  end

  def add(name, note)
    db[note] += [name]
  end

  def grade(note)
    db[note] 
  end

  def sort
    Hash[db.sort.map{ |key,value| [key, value.sort] }]
  end
end
