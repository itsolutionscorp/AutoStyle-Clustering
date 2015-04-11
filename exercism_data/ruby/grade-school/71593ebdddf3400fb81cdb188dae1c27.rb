class School

  attr_reader :db

  def initialize
    @db ||= Hash.new
  end

  def add(name, key)
    grade(key) << name
  end

  def grade(num)
    @db[num] ||= []
  end

  def sort
    Hash[@db.sort].select { |key, name| name.sort! }
  end

end
