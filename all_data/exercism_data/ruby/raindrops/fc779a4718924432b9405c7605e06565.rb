class Raindrops


def self.convert(i)

  if i % 3 == 0 && i % 5 == 0 && i % 7 == 0
    result = "PlingPlangPlong"
  elsif (i % 3 == 0) && (i % 5 == 0)
    result = "PlingPlang"
  elsif i % 3 == 0 && i % 7 == 0
    result = "PlingPlong"
  elsif i % 5 == 0 && i % 7 == 0
    result = "PlangPlong"
  elsif i % 3 == 0
   result = "Pling"
  elsif i % 5 == 0
    result = "Plang"
  elsif i % 7 == 0
    result = "Plong"

  else result = i.to_s
  end
  result
end
end
