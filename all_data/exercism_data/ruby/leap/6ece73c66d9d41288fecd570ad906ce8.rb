class Year

  class << self
    def leap?(year)
      divided_by_fourth_century?(year) || (!divided_by_century?(year) && divided_by_four?(year))
    end

    private
      def divided_by_four?(year)
        divided_by_period?(year, 4)
      end

      def divided_by_century?(year)
        divided_by_period?(year, 100)
      end

      def divided_by_fourth_century?(year)
        divided_by_period?(year, 400)
      end

      def divided_by_period?(year, period)
        year % period == 0
      end
  end

end
