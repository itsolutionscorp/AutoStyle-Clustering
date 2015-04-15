class Raindrops
  def self.convert(n)
    factors = []
    div = 2
    num = n
    answer = ''
    factors << num if num == 1

    while num > 1
      while num % div == 0
        factors << div
        num /= div
      end
      div += 1
    end

    answer += 'Pling' if factors.include? 3
    answer += 'Plang' if factors.include? 5
    answer += 'Plong' if factors.include? 7

    answer.size > 0 ? answer : n.to_s
  end
end
