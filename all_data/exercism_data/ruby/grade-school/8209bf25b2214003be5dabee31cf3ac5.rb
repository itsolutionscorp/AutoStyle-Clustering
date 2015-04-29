class School

  def initialize
    @storage = {}
  end
  
  def db
    dup_storage
  end

  def add(name, grade)
    grade(grade) << name
  end

  def grade(grade)
    storage[grade] ||= []
  end

  def sort
    Hash[
      storage.sort_by {|grade, students| grade}
             .map {|grade, students| [grade, students.sort]}
    ]
  end

  private

  attr_reader :storage

  def dup_storage
    Marshal.load(Marshal.dump(storage))
  end

end
