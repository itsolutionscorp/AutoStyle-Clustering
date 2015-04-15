class Raindrops
  def self.convert(num)
    answer = ""
    answer += 'Pling' if num % 3 == 0
    answer += 'Plang' if num % 5 == 0
    answer += 'Plong' if num % 7 == 0
    answer.length > 0 ? answer : num.to_s
  end
end
