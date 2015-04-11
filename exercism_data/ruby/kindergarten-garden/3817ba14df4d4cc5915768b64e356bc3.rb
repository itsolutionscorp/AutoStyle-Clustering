class Garden
  PLANTS = %w(grass clover radishes violets)
  
  def initialize diagram, students=nil
    default_students = %w(
      Alice Bob Charlie David
      Eve Fred Ginny Harriet 
      Ileana Joseph Kincaid Larry
    )
    students ||= default_students
    @students = students.map(&:downcase).sort
    @diagram = diagram
  end
  
  private
  attr_reader :students, :diagram
  def plant id
    PLANTS.find{|p| p.start_with? id }.to_sym
  end
  
  def garden
    @garden ||= garden_rows.map{ |row| parse_garden row }
  end
  
  def parse_garden garden
    garden.chars.map{|id| plant id }
  end
  
  def garden_rows
    diagram.downcase.split("\n")
  end
  
  def plot_number student
    students.index(student) * 2
  end
  
  def plot_plants plot_number
    [
      garden[0][plot_number..plot_number+1], 
      garden[1][plot_number..plot_number+1]
    ].flatten
  end
  
  def method_missing sym, *args, &block
    if students.include?(sym.to_s)
      plot_plants plot_number(sym.to_s)
    else
      super
    end
  end
  
  def respond_to_missing? sym, include_private = false
    students.include?(sym.to_s) || super
  end
end
