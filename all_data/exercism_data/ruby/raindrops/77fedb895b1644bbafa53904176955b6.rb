class Raindrops
  def self.convert(integer)
    result = ''
    result += 'Pling' if divisible_by_n?(integer: integer, base: 3)
    result += 'Plang' if divisible_by_n?(integer: integer, base: 5)
    result += 'Plong' if divisible_by_n?(integer: integer, base: 7)

    result.length > 0 ? result : integer.to_s
  end

  private
    def self.divisible_by_n?(args)
      args[:integer] % args[:base] == 0
    end

end
