class Board
  def initialize(rows)
    @rows = rows
    validate
  end

  def transform
    [].tap do |rows|
      (0..(@rows.length - 1)).each do |idx|
        rows << transformed_row(idx)
      end
    end
  end

  def self.transform(input)
    new(input).transform
  end

  private

  def transformed_row(row_idx)
    @rows[row_idx].chars.map.with_index do |char, col_idx|
      case char
      when *separators
        char
      else
        mine_count(row_idx, col_idx)
      end
    end.join
  end

  def separators
    %w(| + - *)
  end

  def mine_count(i, j)
    count = surroundings(i, j).count('*')
    count > 0 ? count.to_s : ' '
  end

  def surroundings(i, j)
    [].tap do |out|
      (-1..1).to_a.repeated_permutation(2).each do |y, x|
        out << @rows[i + y][j + x]
      end
    end
  end

  def validate
    fail ValueError unless valid_border? && valid_data? && valid_size?
  end

  def valid_border?
    [@rows.first, @rows.last].all? { |row| row =~ /\+(-+)\+/ }
  end

  def valid_data?
    @rows[1..-2].all? { |row| row =~ /\|( |\*)+\|/ }
  end

  def valid_size?
    row_size = @rows.first.length
    @rows.all? { |row| row.length == row_size }
  end
end

class ValueError < StandardError; end
