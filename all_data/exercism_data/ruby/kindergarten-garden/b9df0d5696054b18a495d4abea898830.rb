class Garden
  attr_reader :students

  DEFAULT_STUDENTS = [ "alice", "bob", "charlie", "david", "eve", "fred",
                       "ginny", "harriet", "ileana", "joseph", "kincaid", "larry" ]

  def initialize(input, students=DEFAULT_STUDENTS)
    @input    = input
    @students = students.map(&:downcase).sort
    @first_row, @second_row = formatted_rows
    create_name_methods
  end
 
  private

  def formatted_rows
    @input.split("\n").map do |row|
      row.chars.map { |char| plants.fetch(char) }.each_slice(2).to_a 
    end
  end

   def plants
    {
      "R" => :radishes,
      "C" => :clover,
      "V" => :violets,
      "G" => :grass
    }
  end

  # plant_owner.fetch('alice') => [:radishes, :clover, :violet, :grass]
  def plant_owner
    both_rows = @first_row.zip(@second_row).map { |a,b| a + b }
    Hash[students.zip both_rows] 
  end

  def create_name_methods
    students.each do |student|
      self.class.send(:define_method, student) do
        plant_owner.fetch(student)
      end
    end
  end

end
