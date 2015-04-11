class Fixnum
  ROMAN_SETS = [
    %w[I V X],
    %w[X L C],
    %w[C D M],
    %w[M ? ?]
  ]
  def to_roman
    i = self
    ROMAN_SETS.collect do |letters|
      calculate_roman(i % 10, letters).tap { i /= 10 }
    end.reverse.join
  end
  private

  def calculate_roman(i, letters)
    case i
    when (1..3) then letters[0] * i
    when 4      then "#{letters[0]}#{letters[1]}"
    when 5      then letters[1]
    when (6..8) then "#{letters[1]}#{letters[0] * (i-5)}"
    when 9      then "#{letters[0]}#{letters[2]}"
    end
  end
end
