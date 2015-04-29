class Matrix

  attr_accessor :string

  def initialize string
    @string = string
  end

  def rows
    array
  end

  def columns 
    array.transpose
  end

private

  def array
    string.split( "\n" ).map { |row| row.split.map &:to_i }
  end

end
