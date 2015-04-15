class Matrix
  def initialize(string)
    @string = string.split("\n").map{|x| x.split(' ')}.each{|x| x.map!{|y| y.to_i}}
  end
  attr_reader :string

  def rows
    string
  end

  def columns
    ans = []
    (0...string[0].length).each do |sub|
      (0...string.length).each_with_object([]) do |position, column|
        column << string[position][sub]
        ans << column
      end
    end
    ans.uniq
  end
end
