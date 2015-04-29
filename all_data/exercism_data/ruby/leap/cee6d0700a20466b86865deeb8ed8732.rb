class Year
  def self.leap?(year)
    if year % 4 == 0
      if year % 100 == 0

        if year % 400 == 0
          return true
        else
          return false
        end

      else
        return true
      end
    end

    return false
  end
end

# class Year
#   def self.leap?(year)
#     if year % 4 == 0
#       if year % 100 != 0
#         return true
#       else
#         if year % 400 == 0
#           return true
#         else
#           return false
#         end
#       end
#     end

#     return false
#   end
# end
