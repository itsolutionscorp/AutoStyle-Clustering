class Matrix
  attr_reader :rows, :columns

  def initialize(input)
    @rows = input.lines.map{|e| e.split.map(&:to_i)}
    @columns = @rows.transpose
  end

  def saddle_points
    @rows.each_with_index.with_object(Array.new) do |(re, ri), result|
      @columns.each_with_index do |ce, ci|
        result << [ri, ci] if re.max.eql? ce.min
      end
    end
  end
end
