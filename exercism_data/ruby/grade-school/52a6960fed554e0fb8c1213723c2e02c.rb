class School
  attr_accessor :db

  def initialize
    @db ||= Hash.new {|session, student| session[student] = []}
  end

  def add(student, session)
    db[session] << student
  end

  def grade(number)
    db[number]
  end

  def sort
    hash = db.rehash
    Hash[hash.sort]
  end

end
