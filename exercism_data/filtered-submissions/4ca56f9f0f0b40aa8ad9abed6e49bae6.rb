def compute(s1,s2)
      contador = 0
      s1 = s1.upcase
      s2 = s2.upcase
      if s1 != s2
        s1.each_char.with_index { |c,i|


          if c != s2[i] && c != nil && s2[i] != nil
            contador += 1
          end
        }
      end
        return contador
    end