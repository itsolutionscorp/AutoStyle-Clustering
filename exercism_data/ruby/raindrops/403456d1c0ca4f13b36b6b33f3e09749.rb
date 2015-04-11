class Raindrops
 def self.convert(input)
 output=""
 if input % 3 == 0 then output+='Pling' end
 if input % 5 == 0 then output+='Plang' end
 if input % 7 == 0 then output+='Plong' end
 if output=="" then output=input.to_s end
 output
 end
end
