class School

  attr_accessor :db

  def initialize
    @db = {}
  end

  def add name, grade
    db[grade].nil? ? db[grade] = [name] : db[grade] << name
  end

  def grade num
    db[num].nil? ? db[num] =[] : db[num]
  end

  def sort
    db.values.sort_by{|v| v.sort!}
    Hash[db.sort_by { |k, v | k}]
  end
end
