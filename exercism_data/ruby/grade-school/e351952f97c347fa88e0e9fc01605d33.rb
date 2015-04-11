class School

  def initialize(name)
    self.name = name
    self.classes = Hash.new {|hash, key| hash[key] = []}
  end

  def add(name, grade_number)
    classes[grade_number] << name
  end

  def db
    classes
  end

  def grade(grade_number)
    classes[grade_number]
  end

  def sort
    Hash[classes.sort].tap do |sorted_classes|
      sorted_classes.each {|grade_number, students| students.sort!}
    end
  end

  private

  attr_accessor :classes, :name
end
