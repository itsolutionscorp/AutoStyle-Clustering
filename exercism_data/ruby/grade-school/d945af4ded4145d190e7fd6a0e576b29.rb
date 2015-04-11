class School
  attr_reader :db

  def initialize
    @db ||= {}
  end

  def add( name, grade )
    db.key?(grade) ? db[grade] << name : db[grade] = [name]  
  end

  def grade(level)
    db[level] ? db[level] : []
  end

  def sort

    database = db.sort
    sort_children(database)

    database.each_with_object({}) do |grade, db| 
      db.merge!(grade[0] => grade[1]) 
    end

  end

private

  def sort_children(db)
    db.each {|grade| grade[1].sort!}
  end
end
