#x = ['a', 'A', 'cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream', 'a', 'A', 'A', 'A', 'a', 'hello', 'Hello', 'HeLLO', 'HeeLO', 'HELLO', 'hello',] 

def combine_anagrams(x)
    o = []
    x.each do |i|
        print i
        puts
#        print o
 #       puts
        catch (:inserted) do
            o.each_index do |j| 
#                print o[j]
#                puts
                if i.downcase.chars.sort.join.eql? o[j][0].downcase.chars.sort.join
                    o[j]+=[i]
                    puts "inserted "+i
                    throw :inserted
                end
            end
            o+=[[i]]
            puts "added "+i
            
        end
            puts
    end
    return o
end
                

            
