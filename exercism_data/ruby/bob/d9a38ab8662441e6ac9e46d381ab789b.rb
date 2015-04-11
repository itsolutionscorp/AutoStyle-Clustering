class Bob

   def initialize
      @responses =
      {
         "default"  => 'Whatever.',
         "question" => 'Sure.',
         "null"     => 'Fine. Be that way!',
         "yell"     => 'Woah, chill out!'
      }
   end


   def question?( statement )
      return( statement[-1,1] == '?' )
   end


   def null?( statement )
      statement.each_char{ | c |
         if ( c != ' ' )
            return( false )
         end
      }
      return( true )
   end


   def yell?( statement )
      yelling  = true
      one_char = false

      statement.each_char{ | c |
         is_letter = /[A-Za-z]/.match( c )

         if ( is_letter != nil )
            c_cap = c.upcase
            if ( c != c_cap )
               yelling = false
            else
               one_char = true
            end
         end

         if ( !yelling )
            return( false )
         end
      }

      if ( one_char )
         return( true )
      else
         return( false )
      end
   end


   def hey( statement )
      if ( question?( statement ) )
         if ( yell?( statement ) )
            current_response = @responses["yell"]
         else
            current_response = @responses["question"]
         end

      elsif ( yell?( statement ) )
         current_response = @responses["yell"]

      elsif ( null?( statement ) )
         current_response = @responses["null"]

      else
         current_response = @responses["default"]
      end

      return( current_response )
   end

end
