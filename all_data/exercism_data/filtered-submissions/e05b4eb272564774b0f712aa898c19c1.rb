def compute (first, second)

       first = first.scan /\w/
       second = second.scan /\w/


















        difference = 0


        first.each_with_index {|val, index|
          if val != second[index]

            if !second[index].nil?
              difference = difference + 1
            end
          end
        }

        difference

     end