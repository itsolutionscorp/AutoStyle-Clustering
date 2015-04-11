class Matrix
  attr_reader :rows, :columns

  def initialize(plaintext)
    @rows = plaintext.split("\n").map do |line|
      line.split(' ').map(&:to_i)
    end
    @columns = @rows.transpose
  end
end
