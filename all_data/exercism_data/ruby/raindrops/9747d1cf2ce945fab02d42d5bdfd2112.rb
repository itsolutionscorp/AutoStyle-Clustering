class Raindrops
  def self.convert(n)
    answer  = ''
    answer += 'Pling'  if n%3 == 0
    answer += 'Plang'  if n%5 == 0
    answer += 'Plong'  if n%7 == 0
    answer += n.to_s() if answer == ''
    answer
  end
end
