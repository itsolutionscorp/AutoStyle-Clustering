class Garden
  def initialize(diagram, students = STUDENTS, plot_width = 2)
    @rows       = diagram.lines.map {|string| string.strip.chars }
    @students   = students.sort
    @plot_width = plot_width
  end

  private

  attr_reader :rows, :students, :plot_width

  def method_missing(name, *args, &block)
    garden.fetch(name){ super name, *args, &block }
  end

  def plots
    @plots ||= sliced_rows.inject do |result, row|
      r = result.zip(row)
    end.map(&:flatten)
  end

  def garden
    @garden ||= Hash[students_as_symbols.zip(plots_as_plant_names)]
  end

  def sliced_rows
    rows.map {|row| row.each_slice(plot_width) }
  end

  def plots_as_plant_names
    plots.map {|plot| plot.map {|letter| PLANTS[letter] }}
  end

  def students_as_symbols
    students.map {|student| student.downcase.to_sym }
  end


  PLANTS = {
    "R" => :radishes,
    "G" => :grass,
    "C" => :clover,
    "V" => :violets
  }

  STUDENTS = %w[
    Alice 
    Bob 
    Charlie 
    David 
    Eve 
    Fred 
    Ginny 
    Harriet 
    Ileana 
    Joseph 
    Kincaid 
    Larry
  ]

end
